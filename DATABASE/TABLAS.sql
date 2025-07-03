--DROP DATABASE LA_CANTERA
/*
CREATE DATABASE LA_CANTERA

USE LA_CANTERA
*/

/*****************************************/
/***********Para algo General*************/
/*****************************************/

CREATE TABLE Constante
(
	nConstanteId				INT PRIMARY KEY IDENTITY(1,1)
	,nConsCod					INT NOT NULL
	,cConsValor					VARCHAR(150)
)


/*****************************************/
/****************Usuarios*****************/
/*****************************************/
CREATE TABLE Privilegios
(
	nPrivilegiosId				INT PRIMARY KEY IDENTITY(1,1)
	,cDescripcion				VARCHAR(200) NOT NULL
	,cObservacion				VARCHAR(200)
)

CREATE TABLE Cargo (
	nCargoId					INT PRIMARY KEY IDENTITY(1,1),
	cNombreCargo				VARCHAR(100) NOT NULL, 
	cDescripcion				VARCHAR(200),
	bEstado						BIT NOT NULL
);

CREATE TABLE Turno (
	nTurnoId					INT PRIMARY KEY IDENTITY(1,1),
	cNombre						VARCHAR(50) NOT NULL, -- "Mañana", "Tarde", "Noche"
	tHoraInicio					TIME NOT NULL,
	tHoraFin					TIME NOT NULL,
	bEstado						BIT NOT NULL
)

CREATE TABLE Persona
(
	nPersonaId					INT PRIMARY KEY IDENTITY(1,1)
	,cNombres					VARCHAR(50) NOT NULL
	,cApePaterno				VARCHAR(50) NOT NULL
	,cApeMaterno				VARCHAR(50) NOT NULL
	,dFechaNacimiento			DATE
	,cDireccion					VARCHAR(200)
	,cTelefono					VARCHAR(9)
	,cDni						VARCHAR(8) NOT NULL
	,cRuc						VARCHAR(20)
	,cCorreo					VARCHAR(200)
)

CREATE TABLE Cliente (
	nClienteId					INT PRIMARY KEY IDENTITY(1,1),
	nPersonaId					INT NOT NULL,
	cObservacion				VARCHAR(200),

	FOREIGN KEY (nPersonaId) REFERENCES Persona(nPersonaId)
)


CREATE TABLE Empleado (
	nEmpleadoId					INT PRIMARY KEY IDENTITY(1,1),
	nPersonaId					INT NOT NULL,
	nCargoId					INT NOT NULL,
	fechaIngreso				DATE,
	sueldoMensual				DECIMAL(10,2),
	activo						BIT DEFAULT 1,

	FOREIGN KEY (nPersonaId) REFERENCES Persona(nPersonaId),
	FOREIGN KEY (nCargoId) REFERENCES Cargo(nCargoId)
)

--(turno por empleado por fecha)
CREATE TABLE AsignacionTurno (
	nAsignacionTurnoId			INT PRIMARY KEY IDENTITY(1,1),
	nEmpleadoId					INT NOT NULL,
	nTurnoId					INT NOT NULL,
	fechaAsignada				DATE NOT NULL,
	asistio						BIT DEFAULT 0, -- ¿vino ese día? //en el front-end, hacer como una marcacion, si marca, se cambia a 1, y si no marca en todo el día, 0

	FOREIGN KEY (nEmpleadoId) REFERENCES Empleado(nEmpleadoId),
	FOREIGN KEY (nTurnoId) REFERENCES Turno(nTurnoId)
)


--si no asistió
CREATE TABLE JustificacionFalta (
	nJustificacionFaltaId		INT PRIMARY KEY IDENTITY(1,1),
	nAsignacionTurnoId			INT NOT NULL,
	cMotivo						VARCHAR(200) NOT NULL,
	dFechaRegistro				DATETIME DEFAULT GETDATE(),

	FOREIGN KEY (nAsignacionTurnoId) REFERENCES AsignacionTurno(nAsignacionTurnoId)
)

/*
	1. Admin asigna turnos diarios o semanales → INSERT en AsignacionTurno
	2. Trabajador marca asistencia → UPDATE asistio = 1, horaMarcacion = NOW()
	3. Si no asistió, puede justificar → INSERT en JustificacionFalta
*/


CREATE TABLE Usuarios
(
	nUsuarioId					INT PRIMARY KEY IDENTITY(1,1)
	,nPrivilegioId				INT NOT NULL
	,nTurnoId					INT NOT NULL
	,nPersonaId					INT NOT NULL
	,cCodUsuario				VARCHAR(4) NOT NULL
	,cPassword					VARCHAR(MAX) NOT NULL
	,nEstado					INT NOT NULL

	FOREIGN KEY (nPrivilegioId) REFERENCES Privilegios(nPrivilegiosId),
	FOREIGN KEY (nPersonaId) REFERENCES Persona(nPersonaId),
	FOREIGN KEY (nTurnoId) REFERENCES Turno(nTurnoId)
)



/*****************************************/
/*****************Pedidos*****************/
/*****************************************/
CREATE TABLE Mesa
(
	nMesaId						INT PRIMARY KEY IDENTITY(1,1)
	,nNumeroMesa				INT NOT NULL
	,bocupado					BIT  NOT NULL --1=Ocupado, 0=Libre, 2=Reservado
)

CREATE TABLE Delivery (
	nDeliveryId					INT PRIMARY KEY IDENTITY(1,1),
	cNombre						VARCHAR(100),
	cTelefono					VARCHAR(15),
)

CREATE TABLE EstadoPedido (
	nEstadoPedidoId				INT PRIMARY KEY IDENTITY(1,1),
	cDescripcion				VARCHAR(50) NOT NULL
)


CREATE TABLE TiposPedidos
(
	nTiposPedidosId				INT PRIMARY KEY IDENTITY(1,1)
	,cDescripcion				VARCHAR(150) NOT NULL
	,bEstado					BIT
)

CREATE TABLE CategoriaProducto
(
	nCategoriaProductoId		INT PRIMARY KEY IDENTITY(1,1)
	,cDescripcion				VARCHAR(150) NOT NULL
	,bEstado					BIT
)

CREATE TABLE Producto
(
	nProductoId					INT PRIMARY KEY IDENTITY(1,1)
	,nUsuarioRegistroId			INT NOT NULL
	,nCategoriaProductoId		INT NOT NULL
	,cNombreProducto			VARCHAR(150) NOT NULL
	,nPrecio					DECIMAL(10,2) NOT NULL
	,bEstado					BIT NOT NULL
	,dFechaRegistro				DATETIME DEFAULT GETDATE() NOT NULL

	FOREIGN KEY (nUsuarioRegistroId) REFERENCES Usuarios(nUsuarioId),
	FOREIGN KEY (nCategoriaProductoId) REFERENCES CategoriaProducto(nCategoriaProductoId)
)

CREATE TABLE Adicional (
	nAdicionalId				INT PRIMARY KEY IDENTITY(1,1)
	,nUsuarioRegistroId			INT NOT NULL
	,cNombre					VARCHAR(100) NOT NULL
	,nPrecio					DECIMAL(10,2) NOT NULL
	,bEstado					BIT NOT NULL
	,dFechaRegistro				DATETIME DEFAULT GETDATE() NOT NULL

	FOREIGN KEY (nUsuarioRegistroId) REFERENCES Usuarios(nUsuarioId)
)

CREATE TABLE Pedidos
(
	nPedidosId					INT PRIMARY KEY IDENTITY(1,1)
	,nEstadoPedidoId			INT NOT NULL -- 0:'Pendiente', 1:'Cocina', 2:'Entregado', 3:'Cobrado'
	,nUsuarioRegistroId			INT NOT NULL
	,nTiposPedidosId			INT NOT NULL
	,nIdMesa					INT NOT NULL
	,nDeliveryId				INT NULL
	,nClienteId					INT NULL
	,dFechaRegistro				DATETIME DEFAULT GETDATE()
	,nTotal						DECIMAL(10,2)

	FOREIGN KEY (nEstadoPedidoId) REFERENCES EstadoPedido(nEstadoPedidoId),
	FOREIGN KEY (nUsuarioRegistroId) REFERENCES Usuarios(nUsuarioId),
	FOREIGN KEY (nTiposPedidosId) REFERENCES TiposPedidos(nTiposPedidosId),
	FOREIGN KEY (nIdMesa) REFERENCES Mesa(nMesaId),
	FOREIGN KEY (nDeliveryId) REFERENCES Delivery(nDeliveryId),
	FOREIGN KEY (nClienteId) REFERENCES Cliente(nClienteId)
)

CREATE TABLE PedidoDetalle
(
	nPedidoDetalleId			INT PRIMARY KEY IDENTITY(1,1)
	,nUsuarioRegistroId			INT NOT NULL
	,nIdPedidosId				INT NOT NULL
	,nIdProducto				INT NOT NULL
	,nCantidad					INT NOT NULL
	,nPrecioUnitarioVenta 		DECIMAL(10,2) NOT NULL
	,cComentario				VARCHAR(500)
	,dFechaRegistro				DATETIME DEFAULT GETDATE()

	FOREIGN KEY (nUsuarioRegistroId) REFERENCES Usuarios(nUsuarioId),
	FOREIGN KEY (nIdPedidosId) REFERENCES Pedidos(nPedidosId),
	FOREIGN KEY (nIdProducto) REFERENCES Producto(nProductoId)
)

CREATE TABLE PedidoAdicional
(
	nPedidoAdicionalId			INT PRIMARY KEY IDENTITY(1,1)
	,nUsuarioRegistroId			INT NOT NULL
	,nPedidosId					INT NOT NULL
	,nPedidoDetalleId			INT NOT NULL
	,nAdicionalId				INT NOT NULL
	,dFechaRegistro				DATETIME DEFAULT GETDATE() NOT NULL

	FOREIGN KEY (nUsuarioRegistroId) REFERENCES Usuarios(nUsuarioId),
	FOREIGN KEY (nPedidosId) REFERENCES Pedidos(nPedidosId),
	FOREIGN KEY (nPedidoDetalleId) REFERENCES PedidoDetalle(nPedidoDetalleId),
	FOREIGN KEY (nAdicionalId) REFERENCES Adicional(nAdicionalId)
)


--Historial de estados del pedido (seguimiento por pasos)

CREATE TABLE PedidoEstadoHistorial (
	nPedidoEstadoHistorialId		INT PRIMARY KEY IDENTITY(1,1),
	nPedidoId						INT NOT NULL,
	nEstadoPedidoId					INT NOT NULL,
	dFechaCambio					DATETIME DEFAULT GETDATE(),
	nUsuarioResponsableId			INT NOT NULL,

	FOREIGN KEY (nPedidoId) REFERENCES Pedidos(nPedidosId),
	FOREIGN KEY (nEstadoPedidoId) REFERENCES EstadoPedido(nEstadoPedidoId),
	FOREIGN KEY (nUsuarioResponsableId) REFERENCES Usuarios(nUsuarioId)
)


CREATE TABLE PedidoCancelado (
	nPedidoCanceladoId				INT PRIMARY KEY IDENTITY(1,1),
	nPedidoId						INT NOT NULL,
	nUsuarioId						INT NOT NULL,
	cMotivo							VARCHAR(200) NOT NULL,
	dFechaCancelacion				DATETIME DEFAULT GETDATE(),

	FOREIGN KEY (nPedidoId) REFERENCES Pedidos(nPedidosId),
	FOREIGN KEY (nUsuarioId) REFERENCES Usuarios(nUsuarioId)
)

-- DESCUENTOS O PROMOCIONES:  “2x1 en bebida”, “10% descuento en combos”.
CREATE TABLE Descuento (
	nDescuentoId				INT PRIMARY KEY IDENTITY(1,1),
	cDescripcion				VARCHAR(150),
	nPorcentaje					DECIMAL(5,2), -- por ejemplo: 10.00 = 10%
	bActivo						BIT
)

CREATE TABLE ProductoDescuento (
	nProductoDescuentoId		INT PRIMARY KEY IDENTITY(1,1),
	nProductoId					INT NOT NULL,
	nDescuentoId				INT NOT NULL,
	fechaInicio					DATE,
	fechaFin					DATE,

	FOREIGN KEY (nProductoId) REFERENCES Producto(nProductoId),
	FOREIGN KEY (nDescuentoId) REFERENCES Descuento(nDescuentoId)
)

/*****************************************/
/**************Inventarios****************/
/*****************************************/

CREATE TABLE CategoriaInsumo (
	nCategoriaInsumoId			INT PRIMARY KEY IDENTITY(1,1),
	cNombre						VARCHAR(100) NOT NULL, -- Ej: “Perecibles”, “Bebidas”, “Condimentos”
	cDescripcion				VARCHAR(200),
	bEstado						BIT
)

CREATE TABLE Insumo (
	nInsumoId					INT PRIMARY KEY IDENTITY(1,1),
	nCategoriaInsumoId			INT NOT NULL,
	cNombre						VARCHAR(100) NOT NULL,
	cUnidadMedida				VARCHAR(20) NOT NULL,
	nStockActual				DECIMAL(10,2) DEFAULT 0,
	nStockMinimo				DECIMAL(10,2) DEFAULT 0,
	bEstado						BIT DEFAULT 1,
	FOREIGN KEY (nCategoriaInsumoId) REFERENCES CategoriaInsumo(nCategoriaInsumoId)
);

CREATE TABLE TipoMovimientoInventario (
	nTipoMovimientoInventarioId INT PRIMARY KEY IDENTITY(1,1),
	cDescripcion				VARCHAR(100) NOT NULL -- Ej: Entrada Compra, Salida Producción, Merma, Ajuste
)


/* Registro de cada entrada/salida del inventario. */
CREATE TABLE MovimientoInventario (
	nMovimientoInventarioId		INT PRIMARY KEY IDENTITY(1,1),
	nInsumoId					INT NOT NULL,
	nTipoMovimientoInventarioId INT NOT NULL,
	nUsuarioId					INT,
	nCantidad					DECIMAL(10,2) NOT NULL,
	dFechaRegistro				DATETIME DEFAULT GETDATE(),
	cObservacion				VARCHAR(200)
	
	FOREIGN KEY (nInsumoId) REFERENCES Insumo(nInsumoId),
	FOREIGN KEY (nTipoMovimientoInventarioId) REFERENCES TipoMovimientoInventario(nTipoMovimientoInventarioId),
	FOREIGN KEY (nUsuarioId) REFERENCES Usuarios(nUsuarioId)
)

--para entradas por proveedores
CREATE TABLE Compra (
	nCompraId					INT PRIMARY KEY IDENTITY(1,1),
	cRazonSocialProveedor		VARCHAR(100),
	cRucProveedor				VARCHAR(11),
	fechaCompra					DATE,
	totalCompra					DECIMAL(10,2),
	nUsuarioId					INT,

	FOREIGN KEY (nUsuarioId) REFERENCES Usuarios(nUsuarioId)
)


CREATE TABLE CompraDetalle (
	nCompraDetalleId			INT PRIMARY KEY IDENTITY(1,1),
	nCompraId					INT NOT NULL,
	nInsumoId					INT NOT NULL,
	nCantidad					DECIMAL(10,2),
	nPrecioUnitario				DECIMAL(10,2),

	FOREIGN KEY (nCompraId) REFERENCES Compra(nCompraId),
	FOREIGN KEY (nInsumoId) REFERENCES Insumo(nInsumoId)
)


CREATE TABLE Merma (
	nMermaId					INT PRIMARY KEY IDENTITY(1,1),
	nInsumoId					INT NOT NULL,
	nCantidad					DECIMAL(10,2),
	cMotivo						VARCHAR(200),
	dFechaRegistro				DATETIME DEFAULT GETDATE(),
	nUsuarioId					INT,

	FOREIGN KEY (nInsumoId) REFERENCES Insumo(nInsumoId),
	FOREIGN KEY (nUsuarioId) REFERENCES Usuarios(nUsuarioId)
)


CREATE TABLE Receta (
	nRecetaId					INT PRIMARY KEY IDENTITY(1,1),
	nProductoId					INT NOT NULL,
	nInsumoId					INT NOT NULL,
	nCantidadInsumo				DECIMAL(10,2) NOT NULL, -- Cantidad usada por 1 unidad vendida
	FOREIGN KEY (nProductoId) REFERENCES Producto(nProductoId),
	FOREIGN KEY (nInsumoId) REFERENCES Insumo(nInsumoId)
)

/*
Lógica de reducción de inventario
	* Cuando se registra un PedidoDetalle,el sistema hace lo siguiente:
	
		- Buscar los insumos requeridos en Receta para ese producto.
	
		- Multiplicar por la cantidad del pedido.
	
		- Insertar un movimiento negativo en MovimientoInventario por cada insumo.

*/