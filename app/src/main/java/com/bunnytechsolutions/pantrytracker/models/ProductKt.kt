//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: pantrytracker.proto

package com.bunnytechsolutions.pantrytracker.models;

@kotlin.jvm.JvmSynthetic
inline fun product(block: com.bunnytechsolutions.pantrytracker.models.ProductKt.Dsl.() -> kotlin.Unit): com.bunnytechsolutions.pantrytracker.models.Product =
  com.bunnytechsolutions.pantrytracker.models.ProductKt.Dsl._create(com.bunnytechsolutions.pantrytracker.models.Product.newBuilder()).apply { block() }._build()
object ProductKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  class Dsl private constructor(
    private val _builder: com.bunnytechsolutions.pantrytracker.models.Product.Builder
  ) {
    companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.bunnytechsolutions.pantrytracker.models.Product.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.bunnytechsolutions.pantrytracker.models.Product = _builder.build()

    /**
     * <code>string id = 1;</code>
     */
    var id: kotlin.String
      @JvmName("getId")
      get() = _builder.getId()
      @JvmName("setId")
      set(value) {
        _builder.setId(value)
      }
    /**
     * <code>string id = 1;</code>
     */
    fun clearId() {
      _builder.clearId()
    }

    /**
     * <code>string barcode = 2;</code>
     */
    var barcode: kotlin.String
      @JvmName("getBarcode")
      get() = _builder.getBarcode()
      @JvmName("setBarcode")
      set(value) {
        _builder.setBarcode(value)
      }
    /**
     * <code>string barcode = 2;</code>
     */
    fun clearBarcode() {
      _builder.clearBarcode()
    }

    /**
     * <code>string brand = 3;</code>
     */
    var brand: kotlin.String
      @JvmName("getBrand")
      get() = _builder.getBrand()
      @JvmName("setBrand")
      set(value) {
        _builder.setBrand(value)
      }
    /**
     * <code>string brand = 3;</code>
     */
    fun clearBrand() {
      _builder.clearBrand()
    }

    /**
     * <code>string name = 4;</code>
     */
    var name: kotlin.String
      @JvmName("getName")
      get() = _builder.getName()
      @JvmName("setName")
      set(value) {
        _builder.setName(value)
      }
    /**
     * <code>string name = 4;</code>
     */
    fun clearName() {
      _builder.clearName()
    }

    /**
     * <code>string size = 5;</code>
     */
    var size: kotlin.String
      @JvmName("getSize")
      get() = _builder.getSize()
      @JvmName("setSize")
      set(value) {
        _builder.setSize(value)
      }
    /**
     * <code>string size = 5;</code>
     */
    fun clearSize() {
      _builder.clearSize()
    }

    /**
     * <code>string ingredients = 6;</code>
     */
    var ingredients: kotlin.String
      @JvmName("getIngredients")
      get() = _builder.getIngredients()
      @JvmName("setIngredients")
      set(value) {
        _builder.setIngredients(value)
      }
    /**
     * <code>string ingredients = 6;</code>
     */
    fun clearIngredients() {
      _builder.clearIngredients()
    }

    /**
     * <code>string nutritionFacts = 7;</code>
     */
    var nutritionFacts: kotlin.String
      @JvmName("getNutritionFacts")
      get() = _builder.getNutritionFacts()
      @JvmName("setNutritionFacts")
      set(value) {
        _builder.setNutritionFacts(value)
      }
    /**
     * <code>string nutritionFacts = 7;</code>
     */
    fun clearNutritionFacts() {
      _builder.clearNutritionFacts()
    }

    /**
     * An uninstantiable, behaviorless type to represent the field in
     * generics.
     */
    @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
    class ImagesProxy private constructor() : com.google.protobuf.kotlin.DslProxy()
    /**
     * <code>repeated string images = 8;</code>
     * @return A list containing the images.
     */
    val images: com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>
      @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
      get() = com.google.protobuf.kotlin.DslList(
        _builder.getImagesList()
      )
    /**
     * <code>repeated string images = 8;</code>
     * @param value The images to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addImages")
    fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.add(value: kotlin.String) {
      _builder.addImages(value)
    }
    /**
     * <code>repeated string images = 8;</code>
     * @param value The images to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignImages")
    operator fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.plusAssign(value: kotlin.String) {
      _builder.addImages(value)
    }
    /**
     * <code>repeated string images = 8;</code>
     * @param values The images to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("addAllImages")
    fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.addAll(values: kotlin.collections.Iterable<kotlin.String>) {
      _builder.addAllImages(values)
    }
    /**
     * <code>repeated string images = 8;</code>
     * @param values The images to add.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("plusAssignAllImages")
    operator fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.plusAssign(values: kotlin.collections.Iterable<kotlin.String>) {
      _builder.addAllImages(values)
    }
    /**
     * <code>repeated string images = 8;</code>
     * @param index The index to set the value at.
     * @param value The images to set.
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("setImages")
    operator fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.set(index: kotlin.Int, value: kotlin.String) {
      _builder.setImages(index, value)
    }/**
     * <code>repeated string images = 8;</code>
     */
    @kotlin.jvm.JvmSynthetic
    @kotlin.jvm.JvmName("clearImages")
    fun com.google.protobuf.kotlin.DslList<kotlin.String, ImagesProxy>.clear() {
      _builder.clearImages()
    }}
}
@kotlin.jvm.JvmSynthetic
inline fun com.bunnytechsolutions.pantrytracker.models.Product.copy(block: com.bunnytechsolutions.pantrytracker.models.ProductKt.Dsl.() -> kotlin.Unit): com.bunnytechsolutions.pantrytracker.models.Product =
  com.bunnytechsolutions.pantrytracker.models.ProductKt.Dsl._create(this.toBuilder()).apply { block() }._build()
