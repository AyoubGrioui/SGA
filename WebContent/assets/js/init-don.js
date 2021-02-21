window.onload = function load() {
    var ancienDonateur = document.getElementById("ancienDonateur");
    ancienDonateur.style.display = "none";
  }
  //hide new donator inputs 
  const hide = () =>  {
    var nouveauDonateur = document.getElementById("nouveauDonateur");
    nouveauDonateur.style.display = "none";
      var ancienDonateur = document.getElementById("ancienDonateur");
      ancienDonateur.style.display = "block";
  }
  //show new donator inputs 
  const show = () => {
    var nouveauDonateur = document.getElementById("nouveauDonateur");
    nouveauDonateur.style.display = "block";
      var ancienDonateur = document.getElementById("ancienDonateur");
      ancienDonateur.style.display = "none";
  }

    const listeDesDonateurs = (element) => {
    var moraleListeDiv = document.getElementById("listeMorale");
    var physiqueListeDiv = document.getElementById("listePhysique");
    if(element.value === 'morale'){
        moraleListeDiv.style.display = "block";
        physiqueListeDiv.style.display = "none";
    }else {
        moraleListeDiv.style.display = "none";
        physiqueListeDiv.style.display = "block";
    }
}
  const typeDonateurFunction = (element) => {
      var moraleDiv = document.getElementById("morale");
      var physiqueDiv = document.getElementById("physique");
      if(element.value === 'morale'){
          moraleDiv.style.display = "block";
          physiqueDiv.style.display = "none";
      }else {
            moraleDiv.style.display = "none";
          physiqueDiv.style.display = "block";
      }
  }
  const typePaiement = (element) => {
    var versement = document.getElementById("versement");
    var cheque = document.getElementById("cheque");
    if(element.value === 'versement'){
        versement.style.display = "block";
        cheque.style.display = "none";
    }else if(element.value === 'cheque'){
          versement.style.display = "none";
        cheque.style.display = "block";
    }else if(element.value === 'espece'){
      versement.style.display = "none";
        cheque.style.display = "none";
    }
}