CREATE PROCEDURE sp_registrar_cliente
(
    @DNI VARCHAR(20),
    @Nombre VARCHAR(100),
    @Apellido VARCHAR(100),
    @Email VARCHAR(150) = NULL,
    @Telefono VARCHAR(20) = NULL
)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @ClienteID INT;

    -- Verificar si el cliente ya existe
    SELECT @ClienteID = ClienteID
    FROM Clientes
    WHERE DNI = @DNI AND Activo = 1;

    IF @ClienteID IS NULL
    BEGIN
        -- Insertar nuevo cliente
        INSERT INTO Clientes (DNI, Nombre, Apellido, Email, Telefono)
        VALUES (@DNI, @Nombre, @Apellido, @Email, @Telefono);

        SET @ClienteID = SCOPE_IDENTITY();
    END
    ELSE
    BEGIN
        -- Actualizar datos del cliente existente
        UPDATE Clientes
        SET
            Nombre = @Nombre,
            Apellido = @Apellido,
            Email = ISNULL(@Email, Email),
            Telefono = ISNULL(@Telefono, Telefono)
        WHERE ClienteID = @ClienteID;
    END

    -- Retornar información del cliente
    SELECT
        ClienteID,
        DNI,
        Nombre,
        Apellido,
        Email,
        Telefono,
        'Cliente registrado/actualizado exitosamente' AS Mensaje
    FROM Clientes
    WHERE ClienteID = @ClienteID;
END;
