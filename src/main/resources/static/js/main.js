// Aggiungi l'evento al form di registrazione dati
document.getElementById('data-form').addEventListener('submit', function(event) {
    event.preventDefault();

    let co2 = document.getElementById('co2').value;
    let energy = document.getElementById('energy').value;
    let waste = document.getElementById('waste').value;

    // Invio i dati della poluzione al server
    fetch('/api/pollution/data', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')  // Se usi JWT
        },
        body: JSON.stringify({
            total_co2: co2,
            total_energy: energy,
            total_waste: waste
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Pollution data saved successfully!');
    })
    .catch(error => console.error('Error:', error));
});
