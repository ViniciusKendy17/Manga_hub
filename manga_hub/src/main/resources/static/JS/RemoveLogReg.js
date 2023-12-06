document.addEventListener('DOMContentLoaded', function () {
    var isAuthenticated = false;

    var registro = document.getElementById('registro');
    var logar = document.getElementById('logar');
    
      var token = localStorage.getItem('token');
      if(token){
        isAuthenticated = true;
        updateUI();
      }
      
    function updateUI() {
      if (isAuthenticated) {
        registro.style.display = 'none';
        logar.style.display = 'none';
      } 
    }
  });