import { Footer } from "antd/lib/layout/layout"


const Foter = () => {
    return (
        <Footer
            className='bg-black text-white text-center mt-5'
        >
            © Inventarium {new Date().getFullYear()} Desarrollado por Nicolas Morales
        </Footer>
    )
}

export default Foter