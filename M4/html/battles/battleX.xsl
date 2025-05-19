<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/battle">
    <html>
      <head>
        <title>Battle Report</title>
        <link rel="stylesheet" href="../css/estilos.css" />
      </head>
      <body>
        <h1>Battle Report</h1>
        <pre><xsl:value-of select="summary"/></pre>
        <hr/>
        <h2>Battle Log</h2>
        <pre><xsl:value-of select="log"/></pre>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>

