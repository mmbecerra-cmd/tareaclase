package suda.com.herramientasuda.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import suda.com.herramientasuda.model.Product
import suda.com.herramientasuda.repository.ProductRepository
import java.util.Optional

@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    // Listar todos
    fun list(): List<Product> {
        return productRepository.findAll()
    }

    // Buscar por ID
    fun findById(id: Int): Optional<Product> {
        return productRepository.findById(id)
    }

    // Guardar (Crear)
    fun save(product: Product): Product {
        if (product.description.isNullOrBlank()) {
            throw Exception("La descripción es obligatoria")
        }

        // --- SOLUCIÓN: Usamos una variable local estable ---
        val stockVal = product.stock
        if (stockVal != null && stockVal < 0) {
            throw Exception("El stock no puede ser negativo")
        }

        return productRepository.save(product)
    }

    // Actualizar
    fun update(id: Int, productDetails: Product): Product {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { Exception("Producto no encontrado con el ID: $id") }

        if (productDetails.description.isNullOrBlank()) {
            throw Exception("La descripción es obligatoria")
        }

        // Si también quieres validar el stock al actualizar, puedes hacer lo mismo:
        val stockVal = productDetails.stock
        if (stockVal != null && stockVal < 0) {
            throw Exception("El stock no puede ser negativo")
        }

        // Creamos una copia actualizada manteniendo el ID original
        val updatedProduct = existingProduct.copy(
            description = productDetails.description,
            price = productDetails.price,
            stock = productDetails.stock
        )

        return productRepository.save(updatedProduct)
    }

    // Eliminar
    fun delete(id: Int) {
        if (!productRepository.existsById(id)) {
            throw Exception("No se puede eliminar, el producto con ID $id no existe")
        }
        productRepository.deleteById(id)
    }
}