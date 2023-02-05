function toggleAnswer(elem) {
    const answer = elem.nextElementSibling;
    if (answer.style.display === "block") {
        answer.style.display = "none";
        elem.firstChild.innerHTML = "+";
    } else {
        answer.style.display = "block";
        elem.firstChild.innerHTML = "-";
    }
}