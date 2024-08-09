import { Categoria } from "./Categoria"

export class Producto {
   id : Number
   codigo : Number
   nombre : String
   descripcion : String
   marca : String
   precio : Number
   cantidad : Number
   borrado : Boolean
   categoria : Categoria
}