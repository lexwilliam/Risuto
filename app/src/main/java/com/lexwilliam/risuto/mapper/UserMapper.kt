package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.user.UserProfile
import com.lexwilliam.risuto.model.UserProfilePresentation
import javax.inject.Inject

interface UserMapper {
    fun toPresentation(profile: UserProfile): UserProfilePresentation
}

class UserMapperImpl @Inject constructor(): UserMapper {

    override fun toPresentation(profile: UserProfile): UserProfilePresentation =
        UserProfilePresentation(toPresentation(profile.data))

    private fun toPresentation(profile: UserProfile.Data): UserProfilePresentation.Data =
        UserProfilePresentation.Data(profile.birthday, profile.gender, toPresentation(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, profile.url, profile.username)

    private fun toPresentation(images: UserProfile.Data.Images): UserProfilePresentation.Data.Images =
        UserProfilePresentation.Data.Images(toPresentation(images.jpg), toPresentation(images.webp))

    private fun toPresentation(jpg: UserProfile.Data.Images.Jpg): UserProfilePresentation.Data.Images.Jpg =
        UserProfilePresentation.Data.Images.Jpg(jpg.image_url)

    private fun toPresentation(webp: UserProfile.Data.Images.Webp): UserProfilePresentation.Data.Images.Webp =
        UserProfilePresentation.Data.Images.Webp(webp.image_url)
}