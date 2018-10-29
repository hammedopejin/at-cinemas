package com.planetpeopleplatform.atcinemas.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Reviews(`in`: Parcel) : Parcelable {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("content")
    var content: String? = null
    @SerializedName("url")
    var url: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(author)
        dest.writeString(content)
        dest.writeString(url)

    }

    init {
        id = `in`.readString()
        author = `in`.readString()
        content = `in`.readString()
        url = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<Reviews> {
        override fun createFromParcel(parcel: Parcel): Reviews {
            return Reviews(parcel)
        }

        override fun newArray(size: Int): Array<Reviews?> {
            return arrayOfNulls(size)
        }
    }
}