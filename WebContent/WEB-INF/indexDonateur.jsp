<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tableau de bord</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="assets/js/init-secretaire.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
      <div class="flex flex-col flex-1 w-full">
      	<c:import url="Menu/headerDonateur.jsp"></c:import>
        <main class="h-full pb-16 overflow-y-auto">
           <div class="Menu/container grid px-6 mx-auto">
            <h2
              class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
            >
              Liste de vos dons
            </h2>
            <h4
              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
            >
              Information sur les dons
            </h4>
            <!-- Tableau -->
            
		<div class="w-full overflow-hidden rounded-lg shadow-xs">
          <div class="w-full overflow-x-auto">
            <table class="w-full whitespace-no-wrap">
              <thead>
              <tr
                      class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
              >
                <th class="px-4 py-3">Date</th>
                <th class="px-4 py-3">Montant</th>
                <th class="px-4 py-3">Type de paiement</th>
              </tr>
              </thead>
              <tbody
                      class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
              >
              <c:forEach items="${donList}" var="don" varStatus="boucle" >
                <tr class="text-gray-700 dark:text-gray-400">
                  <td class="px-4 py-3 text-sm"><c:out value="${don.dateDon}"/></td>
                  <td class="px-4 py-3 text-sm"><c:out value="${don.montant}"/></td>
                  <td class="px-4 py-3 text-sm">
                   <c:choose>
                      		<c:when test="${don.getClass().getName() == 'com.sga.entities.DonCheque'}"><c:out value="chèque" /></c:when>
                      		<c:when test="${don.getClass().getName() == 'com.sga.entities.DonEspece'}"><c:out value="espece" /></c:when>
                      		<c:when test="${don.getClass().getName() == 'com.sga.entities.DonVersement'}"><c:out value="versement" /></c:when>
                      	</c:choose>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
          <div
                  class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800"
          ></div>
        </div>
			
          </div>
        </main>
       </div>
      </div>
  </body>
</html>