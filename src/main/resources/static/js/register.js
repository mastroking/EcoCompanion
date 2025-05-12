document.querySelector("#register-form").addEventListener("submit", async function(event) {
    event.preventDefault(); // Impedisce il comportamento normale del form

    // Raccogli i dati del form
    const username = document.querySelector("#username").value.trim();
    const email = document.querySelector("#email").value.trim();
    const password = document.querySelector("#password").value.trim();

    // Verifica che i dati non siano vuoti
    if (!username || !email || !password) {
        alert("Tutti i campi devono essere compilati!");
        return;
    }

    try {
        // Invia una richiesta POST al server
        const response = await fetch("/api/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, email, password })
        });

        // Controlla la risposta del server
        if (response.ok) {
            const result = await response.text(); // Leggi il messaggio di successo
            alert(result); // Mostra il messaggio di successo
            return;
        } else {
            throw new Error("Errore nella registrazione.");
        }
    } catch (error) {
        // Mostra popup in caso di errore
        alert("Error during registration. Please try again later.");
        console.error("Errore durante la registrazione:", error); // Per debug
    }
});
