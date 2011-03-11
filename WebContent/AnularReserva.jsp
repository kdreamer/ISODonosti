<%@ page contentType="text/html;charset=windows-1252"%>
<jsp:useBean id="logNegocio" class="web.AnularReservaBean" scope="request" />
<jsp:setProperty name="logNegocio" property="reserva" param="numReserva"/>
<jsp:getProperty name="logNegocio" property="reserva" />