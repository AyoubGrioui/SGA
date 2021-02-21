<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Liste des entrées</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="assets/js/init-alpine.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
      <c:import url="Menu/sideMenuEntreeSortie.jsp"/>
      
      <div class="flex flex-col flex-1 w-full">
        <c:import url="Menu/headerMenu.jsp"/>
        <main class="h-full pb-16 overflow-y-auto">
          <div class="container grid px-6 mx-auto">
            <h2
              class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
            >
              Liste des entrées
            </h2>
            
            <!-- la liste -->
            <h4
              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
            >
               Les entrées monétaires :
            </h4>
            <div class="w-full overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto" id="listeDesEntrees">
                <table class="w-full whitespace-no-wrap">
                  <thead>
                    <tr
                      class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                    >
                      <th class="px-4 py-3">Donateur</th>
                      <th class="px-4 py-3">Date de récéption</th>                      
                      <th class="px-4 py-3">Montant</th>
                      <th class="px-4 py-3">type de Paiement</th>
                    </tr>
                  </thead>

                  <tbody
                    class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                  >
			<c:forEach items="${donList}" var="don" varStatus="boucle" >
                <tr class="text-gray-700 dark:text-gray-400">
                  <td class="px-4 py-3">
                    <div class="flex items-center text-sm">
                      <div>
                        <p class="font-semibold"><c:out value="${don.donneur.nom}" /></p>
                      </div>
                    </div>
                  </td>
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
          </div>
          	<script src="assets/js/imprimer.js"></script>
			<button
			                onclick="printDiv('listeDesEntrees')"
			                value="imprimer"
			                class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
			              >
			                Imprimer
			</button>
        </main>
      </div>
    </div>
       
  </body>
</html>
