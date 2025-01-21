document.getElementById('login-form').addEventListener('submit', function (event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(err => {
                    alert('Login failed: ' + err);
                    throw new Error(err);
                });
            }
            return response.json();
        })
        .then(data => {
            alert('Login successful!');
            localStorage.setItem('token', data.token); // Salva il token nel localStorage
            window.location.href = '/PollutionTracker'; // Reindirizza alla dashboard
        })
        .catch(error => console.error('Error:', error));
});
