package proyectoFinal;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB_SpaceWars {

    private static String url = "jdbc:mysql://spacewars.chwyik8wac7w.eu-north-1.rds.amazonaws.com:3306/mydb?useSSL=false";
    private static String user = "admin";
    private static String psw = "Jefecolorado123!";

    public Planet loadPlanet(int planetId) {
        Planet planet = null;

        try (Connection conn = DriverManager.getConnection(url, user, psw)) {

            String qPlanet = "SELECT * FROM planet_stats WHERE planet_id = ?";
            PreparedStatement pstPlanet = conn.prepareStatement(qPlanet);
            pstPlanet.setInt(1, planetId);
            ResultSet rs = pstPlanet.executeQuery();

            if (rs.next()) {
                planet = new Planet(
                    rs.getInt("technology_defense_level"),
                    rs.getInt("technology_attack_level"),
                    rs.getInt("resource_metal_amount"),
                    rs.getInt("resource_deuterion_amount")
                );
                ArrayList<MilitaryUnit>[] army = new ArrayList[7];
                for (int i = 0; i < 7; i++) army[i] = new ArrayList<>();
                planet.setArmy(army);
            }

            String qArmy = "SELECT * FROM planet_battle_army WHERE planet_id = ?";
            PreparedStatement pstArmy = conn.prepareStatement(qArmy);
            pstArmy.setInt(1, planetId);
            rs = pstArmy.executeQuery();

            while (rs.next()) {
                int type = rs.getInt("unit_type");
                int armor = rs.getInt("armour");
                int atack = rs.getInt("atack");
                planet.getArmy()[type].add(UnitFactory.createUnit(type, armor, atack));
            }

            String qDefense = "SELECT * FROM planet_battle_defense WHERE planet_id = ?";
            PreparedStatement pstDefense = conn.prepareStatement(qDefense);
            pstDefense.setInt(1, planetId);
            rs = pstDefense.executeQuery();

            while (rs.next()) {
                int type = rs.getInt("unit_type");
                int armor = rs.getInt("armour");
                int atack = rs.getInt("atack");
                planet.getArmy()[type].add(UnitFactory.createUnit(type, armor, atack));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planet;
    }

    public ArrayList<String[]> loadBattleReports(int planetId) {
        ArrayList<String[]> reports = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, psw)) {
            String query = "SELECT DISTINCT num_battle FROM vista_reporte_batalla WHERE planet_id = ? ORDER BY num_battle DESC LIMIT 5";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, planetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int battleId = rs.getInt("num_battle");

                String statsQuery = "SELECT DISTINCT battle_stats FROM vista_reporte_batalla WHERE planet_id = ? AND num_battle = ?";
                PreparedStatement statsStmt = conn.prepareStatement(statsQuery);
                statsStmt.setInt(1, planetId);
                statsStmt.setInt(2, battleId);
                ResultSet rsStats = statsStmt.executeQuery();
                String stats = rsStats.next() ? rsStats.getString("battle_stats") : "";

                String logQuery = "SELECT line_text FROM vista_reporte_batalla WHERE planet_id = ? AND num_battle = ? ORDER BY num_line";
                PreparedStatement logStmt = conn.prepareStatement(logQuery);
                logStmt.setInt(1, planetId);
                logStmt.setInt(2, battleId);
                ResultSet rsLog = logStmt.executeQuery();

                StringBuilder logBuilder = new StringBuilder();
                while (rsLog.next()) {
                    logBuilder.append(rsLog.getString("line_text")).append("\n");
                }

                reports.add(new String[] { stats, logBuilder.toString() });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    public void guardarBatalla(int planetId, String stats, String log) {
        try (Connection conn = DriverManager.getConnection(url, user, psw)) {
            String countQuery = "SELECT COUNT(DISTINCT num_battle) AS total FROM battle_stats WHERE planet_id = ?";
            PreparedStatement countStmt = conn.prepareStatement(countQuery);
            countStmt.setInt(1, planetId);
            ResultSet rs = countStmt.executeQuery();
            rs.next();
            int total = rs.getInt("total");

            if (total >= 5) {
                String deleteBattle = "DELETE FROM battle_log WHERE (planet_id, num_battle) IN (SELECT planet_id, num_battle FROM battle_log WHERE planet_id = ? GROUP BY num_battle ORDER BY num_battle LIMIT 1)";
                PreparedStatement delLog = conn.prepareStatement(deleteBattle);
                delLog.setInt(1, planetId);
                delLog.executeUpdate();

                String deleteStats = "DELETE FROM battle_stats WHERE (planet_id, num_battle) IN (SELECT planet_id, num_battle FROM battle_stats WHERE planet_id = ? ORDER BY num_battle LIMIT 1)";
                PreparedStatement delStats = conn.prepareStatement(deleteStats);
                delStats.setInt(1, planetId);
                delStats.executeUpdate();

                String deleteArmy = "DELETE FROM planet_battle_army WHERE (planet_id, num_battle) IN (SELECT planet_id, num_battle FROM planet_battle_army WHERE planet_id = ? ORDER BY num_battle LIMIT 1)";
                PreparedStatement delArmy = conn.prepareStatement(deleteArmy);
                delArmy.setInt(1, planetId);
                delArmy.executeUpdate();

                String deleteDefense = "DELETE FROM planet_battle_defense WHERE (planet_id, num_battle) IN (SELECT planet_id, num_battle FROM planet_battle_defense WHERE planet_id = ? ORDER BY num_battle LIMIT 1)";
                PreparedStatement delDefense = conn.prepareStatement(deleteDefense);
                delDefense.setInt(1, planetId);
                delDefense.executeUpdate();
            }

            String maxQuery = "SELECT COALESCE(MAX(num_battle), 0) + 1 AS next FROM battle_stats WHERE planet_id = ?";
            PreparedStatement maxStmt = conn.prepareStatement(maxQuery);
            maxStmt.setInt(1, planetId);
            rs = maxStmt.executeQuery();
            rs.next();
            int nextBattleId = rs.getInt("next");

            String insertStats = "INSERT INTO battle_stats (planet_id, num_battle, resource_metal_acquired, resource_deuterion_acquired, battle_stats) VALUES (?, ?, 0, 0, ?)";
            PreparedStatement psStats = conn.prepareStatement(insertStats);
            psStats.setInt(1, planetId);
            psStats.setInt(2, nextBattleId);
            psStats.setString(3, stats);
            psStats.executeUpdate();

            String[] lines = log.split("\\n");
            String insertLog = "INSERT INTO battle_log (planet_id, num_battle, num_line, log_entry) VALUES (?, ?, ?, ?)";
            PreparedStatement psLog = conn.prepareStatement(insertLog);
            for (int i = 0; i < lines.length; i++) {
                psLog.setInt(1, planetId);
                psLog.setInt(2, nextBattleId);
                psLog.setInt(3, i + 1);
                psLog.setString(4, lines[i]);
                psLog.executeUpdate();
            }

            System.out.println("Batalla guardada como num_battle: " + nextBattleId);
            ExportadorXML.exportarBatalla(nextBattleId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class UnitFactory {
    public static MilitaryUnit createUnit(int type, int armor, int atack) {
        return switch (type) {
            case 0 -> new LightHunter(armor, atack);
            case 1 -> new HeavyHunter(armor, atack);
            case 2 -> new Battleship(armor, atack);
            case 3 -> new ArmoredShip(armor, atack);
            case 4 -> new MissileLauncher(armor, atack);
            case 5 -> new IonCannon(armor, atack);
            case 6 -> new PlasmaCannon(armor, atack);
            default -> null;
        };
    }
}