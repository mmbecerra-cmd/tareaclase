package suda.com.herramientasuda.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import suda.com.herramientasuda.model.Product
import suda.com.herramientasuda.service.ProductService

@RestController
@RequestMapping("/api/products")
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    // GET: Listar todos los productos
    @GetMapping
    fun getAllProducts(): List<Product> {
        return productService.list()
    }

    // GET: Buscar un producto por ID (Ej: /api/products/1)
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): Product {
        return productService.findById(id)
            .orElseThrow { RuntimeException("Producto no encontrado") }
    }

    // POST: Crear un nuevo producto
    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.save(product)
    }

    // PUT: Actualizar un producto existente por ID (Ej: /api/products/1)
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Int, @RequestBody productDetails: Product): Product {
        return productService.update(id, productDetails)
    }

    // DELETE: Eliminar un producto por ID (Ej: /api/products/1)
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Int): String {
        productService.delete(id)
        return "Producto eliminado exitosamente"
    }
}