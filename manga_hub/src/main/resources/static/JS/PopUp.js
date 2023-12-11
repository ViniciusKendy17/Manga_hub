document.getElementById('CadastroProduto').addEventListener('click', ChamarPop);

function ChamarPop(){
    event.preventDefault();
    const modal = document.getElementById('out-form')
    modal.classList.add('abrir')

    modal.addEventListener('click',(e) => {
        if (e.target.id == 'fechar' || e.target.id == 'container'){
           modal.classList.remove('abrir')
        }
    })
}

