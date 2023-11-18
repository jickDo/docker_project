# 아파치 웹 서버 이미지를 사용합니다.
FROM httpd:2.4

# 아파치 웹 서버의 기본 HTML 디렉토리를 설정합니다.
WORKDIR /usr/local/apache2/htdocs/

# 호스트 시스템의 프론트엔드 디렉토리에서 컨테이너의 웹 루트 디렉토리로 파일을 복사합니다.
COPY ./DevBlog ./

# Apache 서버가 3000번 포트에서 리스닝하도록 설정 파일을 수정합니다.
RUN sed -i 's/Listen 80/Listen 3000/' /usr/local/apache2/conf/httpd.conf
EXPOSE 3000