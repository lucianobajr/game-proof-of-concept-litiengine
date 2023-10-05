# Use a imagem base oficial do MySQL
FROM mysql:8

# Defina as variáveis de ambiente para configurar o MySQL
ENV MYSQL_ROOT_PASSWORD=dummypassword
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=dummypassword
ENV MYSQL_DATABASE=poc

# Exponha a porta 3306 para permitir conexões externas
EXPOSE 3306

# Copie o arquivo init.sql para a pasta /docker-entrypoint-initdb.d/
COPY ./scripts/init.sql /docker-entrypoint-initdb.d/