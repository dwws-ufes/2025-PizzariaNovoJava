FROM ontop/ontop-endpoint:4.2.2

# Download and install PostgreSQL JDBC driver
RUN mkdir -p /opt/ontop/jdbc
RUN wget -O /opt/ontop/jdbc/postgresql.jar https://jdbc.postgresql.org/download/postgresql-42.7.3.jar

# Set classpath to include the PostgreSQL driver
ENV ONTOP_EXTRA_CLASSPATH=/opt/ontop/jdbc/postgresql.jar
