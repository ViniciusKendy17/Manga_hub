document.addEventListener('DOMContentLoaded', function () {
    var isAuthenticated = false;

   
    var logar = document.getElementById('logar');
    var logout = document.getElementById('botaoout');
    var cadasatroP = document.getElementById('CadastroProduto');
    var user = document.getElementById('');
    
      var token = localStorage.getItem('token');
      if(token){
        isAuthenticated = true;
        updateUI();
      }
      
    function updateUI() {
      if (isAuthenticated) {
       
        logar.style.display = 'none';
        logout.style.display = 'flex';
        cadasatroP.style.display = 'flex'


      } 
    }
  });