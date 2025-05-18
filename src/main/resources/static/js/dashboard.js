document.addEventListener("DOMContentLoaded", function () {
    // График роста урожая
    new Chart(document.getElementById("growthChart"), {
        type: "line",
        data: {
            labels: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн"],
            datasets: [{
                label: "Урожай (т/га)",
                data: [2.1, 2.5, 3.0, 3.8, 4.2, 4.5],
                borderColor: "#0d6efd",
                backgroundColor: "rgba(13,110,253,0.1)",
                fill: true,
                tension: 0.4
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } }
        }
    });

    // График распределения ферм
    new Chart(document.getElementById("farmsChart"), {
        type: "bar",
        data: {
            labels: ["Север", "Юг", "Запад", "Восток"],
            datasets: [{
                label: "Количество ферм",
                data: [12, 8, 15, 10],
                backgroundColor: "#198754"
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: false } }
        }
    });
});
