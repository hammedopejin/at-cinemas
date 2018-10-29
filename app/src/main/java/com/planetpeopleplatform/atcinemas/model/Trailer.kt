package com.planetpeopleplatform.atcinemas.model

import com.google.gson.annotations.SerializedName

class Trailer(key: String, name: String) {

    @SerializedName("key")
    var key: String? = null
        private set

    @SerializedName("name")
    var name: String? = null
        private set

    init {
        this.key = key
        this.name = name
    }

    fun setKey() {
        this.key = key
    }

    fun setName() {
        this.name = name
    }
}