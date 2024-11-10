// Script JS apenas para estilização e animações suaves

// Efeito de foco nos inputs
const inputs = document.querySelectorAll('.form-group input, .form-group select');
inputs.forEach(input => {
    input.addEventListener('focus', function () {
        input.style.borderColor = '#0066cc';
    });
    input.addEventListener('blur', function () {
        input.style.borderColor = '#ccc';
    });
});

// Animação de mensagem de erro (para exibir no futuro com Thymeleaf)
const formMessage = document.getElementById('formMessage');
if (formMessage) {
    formMessage.style.transition = 'opacity 0.5s ease-in-out';
    formMessage.style.opacity = 0;

    setTimeout(() => {
        formMessage.style.opacity = 1;
    }, 2000);
}

// Caso precise adicionar dinamicamente as tarefas no futuro, pode ser usado o seguinte código

// Exemplo de como você pode adicionar tarefas dinamicamente
const tarefasContainer = document.getElementById('tarefas-container');

// Função para criar uma tarefa
function criarTarefa(titulo, descricao) {
    const tarefa = document.createElement('div');
    tarefa.classList.add('tarefa');
    tarefa.innerHTML = `
        <h3>${titulo}</h3>
        <p>${descricao}</p>
    `;
    tarefasContainer.appendChild(tarefa);
}

// Exemplo de tarefas estáticas para mostrar na tela
criarTarefa("Tarefa 1", "Descrição da tarefa 1.");
criarTarefa("Tarefa 2", "Descrição da tarefa 2.");

function previewImage(event) {
    const image = document.getElementById('profile-pic');
    image.src = URL.createObjectURL(event.target.files[0]);
}