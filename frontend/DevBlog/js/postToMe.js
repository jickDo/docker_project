document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('myForm'); // 폼의 ID를 'myForm'으로 가정합니다.

    form.addEventListener('submit', function (e) {
        e.preventDefault(); // 폼의 기본 제출 이벤트를 막습니다.

        // 폼 데이터를 JSON 형식으로 변환합니다.
        const formData = {
            name: document.getElementById('name').value,
            emailAddress: document.getElementById('emailAddress').value,
            companyUrl: document.getElementById('companyUrl').value,
            address: document.getElementById('address').value,
            Job: document.getElementById('Job').value,
            comment: document.getElementById('comment').value
        };
        console.log(JSON.stringify(formData))

        // `fetch`를 사용하여 JSON으로 서버에 데이터를 보냅니다.
        fetch("http://localhost:8080/mail/request", {
            mode: "cors",
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // 서버에 JSON 형식임을 알립니다.
            },
            body: JSON.stringify(formData) // JSON 문자열로 변환한 데이터
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch((error) => console.error('Error:', error));
    });
});
