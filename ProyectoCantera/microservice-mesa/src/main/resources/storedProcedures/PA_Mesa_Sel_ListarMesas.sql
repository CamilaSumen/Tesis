IF OBJECT_ID('PA_Mesa_Sel_ListarMesas') IS NOT NULL
    DROP PROCEDURE PA_Mesa_Sel_ListarMesas
GO
/*---------------------------------------------------------------------------------
PROPÓSITO         | Lista todas las mesas activas
AUTOR             | Jorge Bonifaz
FECHA DE CREACIÓN | 2025-09-03
EJEMPLO:
EXEC PA_Mesa_Sel_ListarMesas
-----------------------------------------------------------------------------------*/
CREATE PROCEDURE PA_Mesa_Sel_ListarMesas
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRY
		BEGIN TRAN

	SELECT
		T1.nMesaId,
		T1.cCodMesa,
		T1.bocupado,
		T1.cCodUsuario,
		ISNULL(T2.PedidoID, 0) AS PedidoID,
		T1.bEstado
	FROM Mesa T1 WITH(NOLOCK)
	LEFT JOIN PEDIDOS T2 WITH(NOLOCK)
		ON T1.CCODMESA = T2.Mesa
		AND (T2.ESTADO <> 'Cancelado' OR T2.ESTADO IS NULL)
	WHERE T1.bEstado = 1

		COMMIT TRAN
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
		DECLARE @ErrorMessage NVARCHAR(4000)
		DECLARE @ErrorSeverity INT
		DECLARE @ErrorState INT

		SELECT
			@ErrorMessage = ERROR_MESSAGE(),
			@ErrorSeverity = ERROR_SEVERITY(),
			@ErrorState = ERROR_STATE()
		RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
	END CATCH
END
