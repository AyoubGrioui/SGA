<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Ajouter Adhérent</title>
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
      <c:import url="Menu/sideMenuAdherent.jsp"></c:import>
      <div class="flex flex-col flex-1">
        <c:import url="Menu/headerMenu.jsp" />
          <main class="h-full pb-16 overflow-y-auto">
          <form method="post" action="">
            <div class="container px-6 mx-auto grid">
              <!-------- Titre --------->
              <h2
                class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Ajouter un adhérent
              </h2>

              <!-- ------------ -->
              <h4
                class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
              >
                Informations Standard
              </h4>

              <div
                class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
              >
                <label class="block mt-3 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Nom</span>
                  <input
                    type="text"
                    name="nomAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="Nom"
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    adherentForm.erreurs['nomAdherent']
                  </span>
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Prenom</span>
                  <input
                    type="text"
                    name="prenomAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="Prenom"
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Email</span>
                  <input
                    type="text"
                    name="emailAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="*******@gmail.com"
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    adherentForm.erreurs['emailAdherent']
                  </span>
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Mot de passe</span
                  >
                  <input
                    type="text"
                    name="motDePasseAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                  <span class="text-xs text-red-600 dark:text-red-400">
                    adherentForm.erreurs['motDePasseAdherent']
                  </span>
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">CIN</span>
                  <input
                    type="text"
                    name="cinAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder="CIN"
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Lieu de naissance</span
                  >
                  <input
                    type="text"
                    name="lieuNaissanceAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Date de naissance</span
                  >
                  <input
                    type="date"
                    name="dateNaissanceAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Numéro de téléphone</span
                  >
                  <input
                    type="text"
                    name="telephoneAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Adresse</span>
                  <input
                    type="text"
                    name="adresseAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Date d'adhésion</span
                  >
                  <input
                    type="date"
                    name="dateadhesionAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Profession</span
                  >
                  <input
                    type="text"
                    name="professionAdherent"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Image</span>
                  <input
                    type="file"
                    name="photoAdherent"
                    class="block mt-2"
                    placeholder=""
                  />
                </label>

                <div class="mt-4 mb-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"> Sexe </span>
                  <div class="mt-2">
                    <label
                      class="inline-flex items-center text-gray-600 dark:text-gray-400"
                    >
                      <input
                        type="radio"
                        class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                        name="sexeAdherent"
                        value="Femme"
                      />
                      <span class="ml-2">Femme</span>
                    </label>
                    <label
                      class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400"
                    >
                      <input
                        type="radio"
                        class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                        name="sexeAdherent"
                        value="homme"
                      />
                      <span class="ml-2">Homme</span>
                    </label>
                  </div>
                </div>
              </div>

              <!-- Fonction -->
              <h4
                class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
              >
                Fonction
              </h4>
              <div
                class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
              >
                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"> Role </span>
                  <select
                    name="roleFonction"
                    class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                  >
                    <option>Président(e)</option>
                    <option>Secrétaire</option>
                    <option>Tréssorie</option>
                    <option>Autre</option>
                  </select>
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400"
                    >Date Début</span
                  >
                  <input
                    type="date"
                    name="dateDebutLigneFonction"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>

                <label class="block mt-4 text-sm">
                  <span class="text-gray-700 dark:text-gray-400">Date Fin</span>
                  <input
                    type="date"
                    name="dateFinLigneFonction"
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    placeholder=""
                  />
                </label>
              </div>

              <div class="mb-8">
                <input
                  type="submit"
                  value="Valider"
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
