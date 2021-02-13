<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Modifier un don</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./assets/css/tailwind.output.css" />
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="./assets/js/init-secretaire.js"></script>
    <script src="./assets/js/init-don.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
      <c:import url="Menu/sideMenuDonateurPage.jsp"></c:import>
      <div class="flex flex-col flex-1">
        <c:import url="Menu/headerSecretaire.jsp"></c:import>

        <main class="h-full pb-16 overflow-y-auto">
          <form methode="" action="">
            <div class="container px-6 mx-auto grid">
              <h2
                class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Modifier le don
              </h2>
              <!-- Don inputs -->
              <h4
                class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
              >
                Formulaire du don
              </h4>
              <div
                class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
              >
              <label class="mt-2 block text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400"
                    >Nom du donateur : 
                  </span>
                  <span class="text-gray-700 font-bold dark:text-gray-100"
                    >Test nom donateur 
                  </span>
                </label>
                <label class="mt-4 block text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400"
                    >Date
                  </span>
                  <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    type="date"
                    name=""
                    value=""
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    Champ invalide (erreur exemple)
                  </span>
                </label>
                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400"
                    >Description</span
                  >
                  <textarea
                    class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-textarea focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                    rows="3"
                    placeholder="Description"
                    name=""
                    value=""
                  ></textarea>
                  <span class="text-xs text-red-600 dark:text-red-400">
                    Champ invalide (erreur exemple)
                  </span>
                </label>
                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400">
                    Type de paiement
                  </span>
                  <select
                    class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                    onchange="typePaiement(this)"
                    name=""
                  >
                    <option value="espece">Espèce</option>
                    <option value="cheque">Chèque</option>
                    <option value="versement">Versement</option>
                  </select>
                </label>
                <div id="versement" style="display: none">
                  <label class="mt-4 block text-sm">
                    <span class="text-gray-700 font-medium dark:text-gray-400"
                      >Numéro du compte banquaire
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="Numéro du compte banquaire"
                      name=""
                      value=""
                    />
                    <span class="text-xs text-red-600 dark:text-red-400">
                      Champ invalide (erreur exemple)
                    </span>
                  </label>
                </div>
                <div id="cheque" style="display: none">
                  <label class="mt-4 block text-sm">
                    <span class="text-gray-700 font-medium dark:text-gray-400"
                      >Numéro du compte banquaire
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="Numéro du compte banquaire"
                      name=""
                      value=""
                    />
                    <span class="text-xs text-red-600 dark:text-red-400">
                      Champ invalide (erreur exemple)
                    </span>
                  </label>
                  <label class="mt-4 block text-sm">
                    <span class="text-gray-700 font-medium dark:text-gray-400"
                      >Date du chèque
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      type="date"
                      name=""
                      value=""
                    />
                    <span class="text-xs text-red-600 dark:text-red-400">
                      Champ invalide (erreur exemple)
                    </span>
                  </label>
                  <label class="mt-4 block text-sm">
                    <span class="text-gray-700 font-medium dark:text-gray-400"
                      >Date du dépot
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      type="date"
                      name=""
                      value=""
                    />
                    <span class="text-xs text-red-600 dark:text-red-400">
                      Champ invalide (erreur exemple)
                    </span>
                  </label>
                  <label class="mt-4 block text-sm">
                    <span class="text-gray-700 font-medium dark:text-gray-400"
                      >Nom de la banque
                    </span>
                    <input
                      class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                      placeholder="nom banque"
                      name=""
                      value=""
                    />
                    <span class="text-xs text-red-600 dark:text-red-400">
                      Champ invalide (erreur exemple)
                    </span>
                  </label>
                </div>
                <label class="mt-4 block text-sm">
                  <span class="text-gray-700 font-medium dark:text-gray-400"
                    >Montant
                  </span>
                  <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  	name=""
                    value=""
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    Champ invalide (erreur exemple)
                  </span>
                </label>
                
              </div>
              <div class="mb-8">
                <input
                  type="submit"
                  value="Enregistrer"
                  class="px-5 py-3 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                />
              </div>
            </div>
          </form>
        </main>
      </div>
    </div>
  </body>
</html>
