package com.erdemtsynduev.remote

import com.google.gson.*
import java.lang.reflect.Field
import java.util.*

fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setFieldNamingStrategy(CustomFieldNamingPolicy())
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .serializeNulls()
        .create()
}

private class CustomFieldNamingPolicy : FieldNamingStrategy {
    override fun translateName(field: Field): String {
        var name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)
        name = name.substring(2, name.length).toLowerCase(Locale.getDefault())
        return name
    }
}