function searchCars() {
    // Get form values
    const pickupAddress = document.getElementById("pickupAddress").value;
    const returnAddress = document.getElementById("returnAddress").value;
    const pickupDate = document.getElementById("pickupDate").value;
    const returnDate = document.getElementById("returnDate").value;
    // Send form values to servlet for filtering
    axios.get("CarSearchServlet", {
        params: {
            pickupAddress: pickupAddress,
            returnAddress: returnAddress,
            pickupDate: pickupDate,
            returnDate: returnDate
        }
    })
        .then(response => {
            // Update car catalog on page with filtered results
            document.getElementById("carCatalog").innerHTML = response.data;
        })
        .catch(error => {
            console.log(error);
        });
}

function showPage(page) {
    const pages = document.getElementsByClassName("pageBreak");
    for (let i = 0; i < pages.length; i++) {
        pages[i].style.display = "none";
    }
    pages[page - 1].style.display = "block";
}

function updateTime() {
    const now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();

    if (hours < 10) {
        hours = "0" + hours;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }

    document.getElementById("time").innerHTML = hours + ":" + minutes + ":" + seconds;
}

setInterval(updateTime, 1000);