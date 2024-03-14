window.onload = function() {
    GenerateNavBar();
}


function GenerateNavBar(){

    let navbarHTML = ""
    let role = getRole();

    switch(role) {
            case "Azubi" :
                navbarHTML = `
                    <div class="logo">
                        <a href="https://www.gso-koeln.de/"><img id="logo" src="https://www.gso-koeln.de/wp-content/uploads/2020/06/cropped-LOGO-GSO_neu.png" alt=""></a>
                    </div>
                    <ul class="navbar-nav">
                        <li class="nav-item"><a href="https://www.gso-koeln.de/">Startseite</a>
                        </li>
                        <li class="nav-item"><a href="#">Nachweise</a>
                        </li>
                    </ul>`;
            break;
            case "Ausbilder" :
                navbarHTML = `
                    <div class="logo">
                        <a href="https://www.gso-koeln.de/"><img id="logo" src="https://www.gso-koeln.de/wp-content/uploads/2020/06/cropped-LOGO-GSO_neu.png" alt=""></a>
                    </div>
                    <ul class="navbar-nav">
                        <li class="nav-item"><a href="https://www.gso-koeln.de/">Startseite</a>
                        </li>
                        <li class="nav-item"><a href="#">Auszubildene</a>
                        </li>
                    </ul>`;
            break;
            case "Prüfer/Lehrer" :
                navbarHTML = `
                    <div class="logo">
                        <a href="https://www.gso-koeln.de/"><img id="logo" src="https://www.gso-koeln.de/wp-content/uploads/2020/06/cropped-LOGO-GSO_neu.png" alt=""></a>
                    </div>
                    <ul class="navbar-nav">
                        <li class="nav-item"><a href="https://www.gso-koeln.de/">Startseite</a>
                        </li>
                        <li class="nav-item"><a href="#">Auszubildene</a>
                        </li>
                    </ul>`;
            break;
            case "Administartor" :
                navbarHTML = `
                    <div class="logo">
                        <a href="https://www.gso-koeln.de/"><img id="logo" src="https://www.gso-koeln.de/wp-content/uploads/2020/06/cropped-LOGO-GSO_neu.png" alt=""></a>
                    </div>
                    <ul class="navbar-nav">
                        <li class="nav-item"><a href="https://www.gso-koeln.de/">Startseite</a>
                        </li>
                        <li class="nav-item"><a href="#">Auszubildene</a>
                        </li>
                        <li class="nav-item"><a href="mangmentAndConfiguration.html">Konfiguration</a>
                        </li>
                    </ul>`;
            break;
            default:
                navbarHTML = `
                <div class="logo">
                    <a href="https://www.gso-koeln.de/"><img id="logo" src="https://www.gso-koeln.de/wp-content/uploads/2020/06/cropped-LOGO-GSO_neu.png" alt=""></a>
                </div>
                <ul class="navbar-nav">
                    <li class="nav-item"><a href="https://www.gso-koeln.de/">Startseite</a>
                    </li>
                    <li class="nav-item"><a href="#">Auszubildene</a>
                    </li>
                    <li class="nav-item"><a href="mangmentAndConfiguration.html">Konfiguration</a>
                    </li>
                </ul>`;
                break;
    }


    document.getElementById("navbarList")
    .insertAdjacentHTML("afterbegin", navbarHTML);
}
