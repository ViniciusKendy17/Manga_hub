class Specialheader extends HTMLElement{
    ConnectedCallback(){
        this.innerHTML = `
        <header>
        <nav>
            <div id="nav-bar">
                
                <div>
                    <img src="" alt="">
                </div>

                <ul>
                    <div class="navbar">
                        <div class="dropdown">
                          <button class="dropbtn">Mangás
                            <i class="fa fa-caret-down">                           
                            </i>
                            <i class="bi bi-caret-down-fill"></i>
                          </button>
                          <div class="dropdown-content">
                            <a href="#">Shounen</a>
                            <a href="#">Seinen</a>
                            <a href="#">Shoujo</a>
                          </div>
                        </div>

                        <div class="dropdown">
                            <button class="dropbtn">Action Figures
                              <i class="fa fa-caret-down"></i>
                            </button>
                            <div class="dropdown-content">
                              <a href="#">Link 1</a>
                              <a href="#">Link 2</a>
                              <a href="#">Link 3</a>
                            </div>
                          </div>

                          <div class="dropdown">
                            <button class="dropbtn">Populares
                              <i class="fa fa-caret-down"></i>
                            </button>
                            <div class="dropdown-content">
                              <a href="#">One Piece</a>
                              <a href="#">Dragon Ball</a>
                              <a href="#">Jujutsu Kaisen</a>
                            </div>
                          </div>
                      </div>
                </ul>
            </div>
  
            <div id="seacrh-bar">

                <div id="bar">
                    <input type="text" placeholder="O que você está buscando ?">
                </div>
                <div class="searchIcon">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                  </svg>
                </div>
                <div id="botao-out-out" >
                  <a class="button-66" role="button" type="submit" href="../../login.html">LogIn</a>
                </div>

               
            </div>
           
        </nav>
    </header>
    `
    }
}

customElements.define('specialheader',Specialheader);
