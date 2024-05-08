import { Footer } from "antd/lib/layout/layout"


const Foter = () => {
    return (
        <Footer
            style={{
                textAlign: 'center',
            }}
            className='bg-black text-white'
        >
            Inventarium {new Date().getFullYear()} Desarrollado por Nicolas Morales
        </Footer>
    )
}

export default Foter