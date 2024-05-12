import { Button, Table, Tooltip } from "antd";
import { DeleteFilled, FileExcelFilled, FilePdfFilled } from "@ant-design/icons"
import "./estilos/tablaProductos.css"
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { useState } from "react";
import { alertPop } from "../../Hooks/util/messages/alerts";
import Menu from "../menu/Menu";

const TablaProductos = (props) => {

    // eslint-disable-next-line react/prop-types
    const { dataSourse, onBorrado } = props
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

    const onSelectProductos = (productsSelected) => {
        setProductosSeleccionados(productsSelected)
    }

    const onDelete = () => {
        alertPop("¿Esta seguro que desea borrar los Productos seleccionados?", "question", () => onBorrado(productosSeleccionados))
    }

    const columns = [
        {
            title: 'Codigo',
            dataIndex: 'codigo',
            width: "13%",
            key: 'codigo',
            render: (codigo) => <h2 className="text-center">{codigo}</h2>
        },
        {
            title: 'Nombre',
            dataIndex: 'name',
            width: "13%",
            key: 'name',
            render: (name) => <h2 className="text-center">{name}</h2>
        },
        {
            title: 'Imagen',
            dataIndex: 'img',
            width: "10%",
            key: 'img',
        },
        {
            title: 'Descripcion',
            dataIndex: 'descripcion',
            width: "15%",
            key: 'descripcion',
        },
        {
            title: 'Marca',
            dataIndex: 'marca',
            width: "10%",
            key: 'marca',
            render: (marca) => <h2 className="text-center">{marca}</h2>
        },
        {
            title: 'Precio',
            dataIndex: 'precio',
            width: "10%",
            key: 'precio',
            sorter: (a, b) => a.precio - b.precio,
            render: (precio) => <h2 className="text-center">{`$ ${precio}`}</h2>
        },
        {
            title: 'Cantidad',
            dataIndex: 'cant',
            width: "10%",
            sorter: (a, b) => a.cant - b.cant,
            render: (cant) => <h2 className="text-center">{cant}</h2>,
            key: 'cant',
        },
        {
            title: 'Generar Informe',
            dataIndex: '',
            width: "15%",
            key: 'generarInforme',
            render: () => 
                <div className="p-0 text-center">
                    <Button title="Generar PDF" className="bg-red-600 btn-rojo-custom text-white xl:w-1/2 sm:w-full "><FilePdfFilled />PDF</Button>
                    <Button title="Generar EXCEL" className="bg-green-600 btn-verde-custom text-white xl:w-1/2 sm:w-full  "><FileExcelFilled />EXCEL</Button>
                </div>
        },
        {
            title: 'Acciones',
            dataIndex: 'Acciones',
            width: "10%",
            key: 'acciones',
            render: () => <>
                <Button title="Editar Producto" className="bg-slate-800 btn-verde-custom text-white">Editar</Button>
         {/*        <Button title="Ver Producto" className="bg-slate-800  text-white">Ver</Button> */}
            </>
        },
    ];

    return (
        <div className="p-5 pt-0 bg-slate-200" 
        style={{
            marginBottom: "5%",
/*          boxShadow: "-1px -1px 55px -16px ", */
        }}> 
           <Menu/>
            <div className="w-full flex justify-end tabla_botonera">
                <Tooltip title={"Borrado Multiple"} >
                    {productosSeleccionados.length > 0 ?
                        <Button className="bg-red-800 btn-bordo-custom text-white" onClick={() => onDelete()}> <DeleteFilled />Borrado Multiple</Button> :
                        <Button disabled><DeleteFilled />Borrado Multiple</Button>
                    }
                </Tooltip>
            </div>
            <Table
                size="small"
                className="overflow-x-scroll"
                rowKey={(product) => product.id}
                dataSource={dataSourse}
                sortDirections={["ascend", "descend"]}
                columns={columns}
                pagination={defaultPagination(dataSourse, 15)}
                rowSelection={{
                    selectedRowKeys: productosSeleccionados,
                    onChange: onSelectProductos,
                }}
                locale={{
                    emptyText: "No se encontraron Productos",
                    filterSearchPlaceholder: 'put',
                    sortTitle:"2cccccccccccccccccccccc",
                }}
            />
        </div>
    )
}

export default TablaProductos