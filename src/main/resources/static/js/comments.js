const dogId = document.getElementById('dogId').value;
const commentsContainer = document.getElementById("commentsContainer");
const allComments = [];

const displayComments = (comments) => {
    commentsContainer.innerHTML = comments.map(
        (c)=> {
            return asComment(c)
        }
    ).join('')
}

function asComment(c) {

    let commentHtml = `<div id="commentCntr-${c.id}">`

    commentHtml += `<h4> ${c.firstName} ${c.lastName} (${c.created})</h4><br>`
    commentHtml += `<p>${c.message}</p>`
    commentHtml+= `</div>`
    return commentHtml;
}

fetch(`http://localhost:8080/api/${dogId}/comments`).
then(response => response.json()).
then(data => {

    for (let comment of data) {
        allComments.push(comment)
    }
    displayComments(allComments)
})
