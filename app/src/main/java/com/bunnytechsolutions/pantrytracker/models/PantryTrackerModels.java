// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pantrytracker.proto

package com.bunnytechsolutions.pantrytracker.models;

public final class PantryTrackerModels {
  private PantryTrackerModels() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Product_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Product_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Entry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Entry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Pantry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Pantry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023pantrytracker.proto\032\037google/protobuf/t" +
      "imestamp.proto\"t\n\007Product\022\017\n\007barcode\030\001 \001" +
      "(\t\022\r\n\005brand\030\002 \001(\t\022\014\n\004name\030\003 \001(\t\022\023\n\013ingre" +
      "dients\030\004 \001(\t\022\026\n\016nutritionFacts\030\005 \001(\t\022\016\n\006" +
      "images\030\006 \003(\t\"\316\001\n\005Entry\022\n\n\002id\030\001 \001(\t\022\023\n\013pr" +
      "oductCode\030\002 \001(\t\022\020\n\010portions\030\003 \001(\005\022+\n\007exp" +
      "ires\030\004 \001(\0132\032.google.protobuf.Timestamp\022\020" +
      "\n\010reminder\030\005 \001(\010\022\027\n\017portionReminder\030\006 \001(" +
      "\010\022:\n\026expirePortionReminders\030\007 \003(\0132\032.goog" +
      "le.protobuf.Timestamp\"f\n\006Pantry\022\n\n\002id\030\001 " +
      "\001(\t\022\014\n\004name\030\002 \001(\t\022+\n\007created\030\003 \001(\0132\032.goo" +
      "gle.protobuf.Timestamp\022\017\n\007entries\030\n \003(\tJ" +
      "\004\010\004\020\nBD\n+com.bunnytechsolutions.pantrytr" +
      "acker.modelsB\023PantryTrackerModelsP\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_Product_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Product_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Product_descriptor,
        new java.lang.String[] { "Barcode", "Brand", "Name", "Ingredients", "NutritionFacts", "Images", });
    internal_static_Entry_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Entry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Entry_descriptor,
        new java.lang.String[] { "Id", "ProductCode", "Portions", "Expires", "Reminder", "PortionReminder", "ExpirePortionReminders", });
    internal_static_Pantry_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Pantry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Pantry_descriptor,
        new java.lang.String[] { "Id", "Name", "Created", "Entries", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
