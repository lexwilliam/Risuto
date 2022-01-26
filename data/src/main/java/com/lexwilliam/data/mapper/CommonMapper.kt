package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.common.*
import com.lexwilliam.domain.model.common.*
import javax.inject.Inject

interface CommonMapper {
    fun toDomain(aired: AiredRepo): Aired
    fun toDomain(demographic: DemographicRepo): Demographic
    fun toDomain(from: FromRepo): From
    fun toDomain(genre: GenreRepo): Genre
    fun toDomain(licensor: LicensorRepo): Licensor
    fun toDomain(producer: ProducerRepo): Producer
    fun toDomain(prop: PropRepo): Prop
    fun toDomain(related: RelatedRepo): Related
    fun toDomain(studio: StudioRepo): Studio
    fun toDomain(theme: ThemeRepo): Theme
    fun toDomain(to: ToRepo): To
}

class CommonMapperImpl @Inject constructor(): CommonMapper {
    override fun toDomain(aired: AiredRepo): Aired =
        Aired(aired.from, toDomain(aired.prop!!), aired.string, aired.to)

    override fun toDomain(demographic: DemographicRepo): Demographic =
        Demographic(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    override fun toDomain(from: FromRepo): From =
        From(from.day, from.month, from.year)

    override fun toDomain(genre: GenreRepo): Genre =
        Genre(genre.mal_id, genre.name, genre.type, genre.url)

    override fun toDomain(licensor: LicensorRepo): Licensor =
        Licensor(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    override fun toDomain(producer: ProducerRepo): Producer =
        Producer(producer.mal_id, producer.name, producer.type, producer.url)

    override fun toDomain(prop: PropRepo): Prop =
        Prop(toDomain(prop.from!!), toDomain(prop.to!!))

    override fun toDomain(related: RelatedRepo): Related =
        Related()

    override fun toDomain(studio: StudioRepo): Studio =
        Studio(studio.mal_id, studio.name, studio.type, studio.url)

    override fun toDomain(theme: ThemeRepo): Theme =
        Theme(theme.mal_id, theme.name, theme.type, theme.url)

    override fun toDomain(to: ToRepo): To =
        To(to.day, to.month, to.year)

}