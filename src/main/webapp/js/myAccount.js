// Get all tab elements
const tabs = document.querySelectorAll('.tab');
const rentedCarList = document.querySelector("#rented-cars");
const rentCarForm = document.querySelector("#rent-car-form");

// Add event listener for each tab
tabs.forEach(tab => {
    tab.addEventListener('click', e => {
        const tabId = e.target.dataset.tab;

        // Remove active class from all tabs and tab contents
        tabs.forEach(tab => {
            tab.classList.remove('active');
            document.getElementById(tab.dataset.tab).classList.remove('active');
        });

        // Add active class to clicked tab and corresponding tab content
        e.target.classList.add('active');
        document.getElementById(tabId).classList.add('active');
    });
});

// Create a rented car element
function createRentedCarEl(car) {
    const carEl = document.createElement("div");
    carEl.innerHTML = `
    <p>Brand: ${car.brand}</p>
    <p>Model: ${car.model}</p>
    <p>Year: ${car.year}</p>
    <p>Price: ${car.price}</p>
  `;
    return carEl;
}

// Display rented cars on the page
function displayRentedCars() {
    const rentedCars = session.getAttribute("rentedCars");
    rentedCarList.innerHTML = "";
    if (rentedCars == null || rentedCars.isEmpty()) {
        rentedCarList.innerHTML = "<p>You currently have no rented cars in our system.</p>";
    } else {
        rentedCars.forEach(car => {
            rentedCarList.appendChild(createRentedCarEl(car));
        });
    }
}

// call the function
displayRentedCars();

rentCarForm.addEventListener("submit", event => {
    event.preventDefault();
    const formData = new FormData(rentCarForm);
    const carId = formData.get("carId");
    // function to send request to rent the car
    alugarCarro(carId)
        .then(response => {
            // handle the response
            if (response.status === 200) {
                displayRentedCars();
            } else {
                // show error message
            }
        })
        .catch(error => {
            // handle the error
        });
});