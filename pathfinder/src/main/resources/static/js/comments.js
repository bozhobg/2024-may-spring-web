const commentsWrapper = document.getElementById('commentCtnr');
const routeId = document.getElementById('routeId').value;

fetchComments();

function fetchComments() {
    document.getElementById('commentCtnr').innerHTML = '';

    let baseUrl = '/api/comments/route';

    fetch(`${baseUrl}/${routeId}`)
        .then(res => res.json())
        .then(data => {
            data.forEach(c => createCommentDiv(c))
        })
        .catch(err => {
            console.log('An error occurred: ' + err)
        })
}

function createCommentDiv(comment) {
    let commentDiv = document.createElement('div');
    commentDiv.classList.add('comment');

    let author = document.createElement('h4');
    let authorContent = document.createTextNode(`${comment['authorFullName']}:`);
    author.appendChild(authorContent);
    commentDiv.append(author);

    let content = document.createElement('p');
    let textContent = document.createTextNode(comment.textContent);
    content.appendChild(textContent);
    commentDiv.append(content);

    commentsWrapper.appendChild(commentDiv);

    let formsDiv = document.createElement('div');
    formsDiv.classList.add('form-inline')
    commentDiv.appendChild(formsDiv);

    if (!comment["approved"]) {
        let formApprove = document.createElement('form');
        let approveBtn = document.createElement('button');
        approveBtn.type = 'submit';
        approveBtn.textContent = 'Approve';
        approveBtn.classList.add('btn', 'bg-success', 'comment-btn');
        approveBtn.dataset.id = comment['id'];

        approveBtn.addEventListener('click', approveHandler);
        formsDiv.appendChild(formApprove);
        formApprove.appendChild(approveBtn)
        formsDiv.appendChild(formApprove);
    }

    let formDelete = document.createElement('form');
    let deleteBtn = document.createElement('button')
    deleteBtn.type = 'submit';
    deleteBtn.textContent = 'Delete';
    deleteBtn.classList.add('btn', 'bg-danger', 'comment-btn');
    deleteBtn.dataset.id = comment['id'];

    deleteBtn.addEventListener('click', deleteHandler);

    formsDiv.appendChild(formDelete);
    formDelete.appendChild(deleteBtn)
    formsDiv.appendChild(formDelete);
}

function postHandler(event) {
    event.preventDefault();

    // TODO: using ajax, currently standard SPRING

    fetchComments();
}

function approveHandler(event) {
    event.preventDefault();

    const baseUrl = 'http://localhost:8080/api/comments/approve/'
    let id = event.target.dataset['id'];

    const http = {
        method: "PATCH",
    }

    fetch(baseUrl + id, http)
        .catch(err => {
            console.log('Error occurred over PATCH: ' + err)
        })

    location.reload();
}

function deleteHandler(event) {
    event.preventDefault();

    const baseUrl = '/api/comments/delete/'
    let id = event.target.dataset['id'];

    const http = {
        method: "DELETE"
    };

    fetch(baseUrl + id, http)
        .catch(err => {console.log('Deletion unsuccessful: ' + err)});

    location.reload();
}


