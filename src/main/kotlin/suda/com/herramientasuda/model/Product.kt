package suda.com.herramientasuda.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null, // <-- Cambiado de Long? a Int?

    val description: String? = null,

    val price: String? = null,

    val stock: Int? = null
)