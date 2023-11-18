document.getElementById('submit-btn').addEventListener('click', function() {
    var email = document.getElementById('mc-email').value; // 이메일 입력값을 가져옵니다
    var formData = new FormData(); // FormData 객체를 생성합니다
    formData.append('email', email); // 이메일 데이터를 추가합니다

    // Fetch API를 사용하여 백엔드로 POST 요청을 보냅니다
    fetch('http://localhost:8080/mail/send', {
        method: 'POST',
        body: formData
    })
        .then(function(response) {
            return response.text(); // 응답 텍스트를 반환합니다
        })
        .then(function(text) {
            console.log(text); // 콘솔에 결과를 출력합니다
            // 성공 메시지를 사용자에게 표시할 수 있습니다
        })
        .catch(function(error) {
            console.error(error); // 오류를 콘솔에 출력합니다
            // 오류 메시지를 사용자에게 표시할 수 있습니다
        });
});