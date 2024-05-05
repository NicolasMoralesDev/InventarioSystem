import { Button, Table } from "antd";
import "./estilos/tablaProductos.css"
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { useState } from "react";

const TablaProductos = (props) => {

    const { dataSourse } = props
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

    const onSelectProductos = (productsSelected) => {
        console.log(productsSelected);
        setProductosSeleccionados(productsSelected)
    }

    const columns = [
        {
            title: 'Codigo',
            dataIndex: 'codigo',
            width: "13%",
            key: 'codigo',
        },
        {
            title: 'Nombre',
            dataIndex: 'name',
            width: "13%",
            key: 'name',
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
        },
        {
            title: 'Precio',
            dataIndex: 'precio',
            width: "10%",
            key: 'precio',
            sorter: (a, b) => a.precio - b.precio,
            render: (precio) => <>{`$ ${precio}`}</>
        },
        {
            title: 'Cantidad',
            dataIndex: 'cant',
            width: "10%",
            sorter: (a, b) => a.cantidad - b.cantidad,
            key: 'cant',
        },
        {
            title: 'Acciones',
            dataIndex: 'address',
            width: "10%",
            key: 'address',
            render: () => <>
                <Button title="Editar" className="bg-green-600 text-white">Editar</Button>
                <Button title="Editar" className="bg-slate-800 text-white">Ver</Button>

            </>
        },
    ];

    return (
        <>
            <Table
                className="overflow-x-scroll"
                rowKey={(product) => product.id}
                dataSource={dataSourse}
                columns={columns}
                pagination={defaultPagination(dataSourse, 15)}
                rowSelection={{
                    selectedRowKeys: productosSeleccionados,
                    onChange: onSelectProductos,
                }}
                locale={{
                    emptyText: "No se encontraron Productos"
                }}
            />
        </>
    )
}

export default TablaProductos