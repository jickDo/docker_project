document.addEventListener('DOMContentLoaded', function () {
    const submitBtn = document.getElementById('submit-btn');
    const form = document.getElementById('mc-form');

    submitBtn.addEventListener('click', function (e) {
        e.preventDefault();

        const formdata = {
            email: document.getElementById('email').value
        };

        console.log(JSON.stringify(formdata));

        fetch("http://localhost:8080/mail/send", {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formdata),
        })
            .then(response => {
                // 상태 코드가 200인지 확인합니다.
                if (response.ok) {
                    // JSON으로 응답을 파싱합니다.
                    return response.json();
                } else {
                    // 응답이 200이 아니면 에러를 던집니다.
                    throw new Error('Network response was not ok.');
                }
            })
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });
});
