package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.common.*
import com.lexwilliam.data_remote.model.common.*
import javax.inject.Inject

interface CommonMapper {
    fun toRepo(aired: AiredResponse): AiredRepo
    fun toRepo(demographic: DemographicResponse): DemographicRepo
    fun toRepo(from: FromResponse): FromRepo
    fun toRepo(genre: GenreResponse): GenreRepo
    fun toRepo(licensor: LicensorResponse): LicensorRepo
    fun toRepo(producer: ProducerResponse): ProducerRepo
    fun toRepo(prop: PropResponse): PropRepo
    fun toRepo(related: RelatedResponse): RelatedRepo
    fun toRepo(studio: StudioResponse): StudioRepo
    fun toRepo(theme: ThemeResponse): ThemeRepo
    fun toRepo(to: ToResponse): ToRepo
}

class CommonMapperImpl @Inject constructor(): CommonMapper {
    override fun toRepo(aired: AiredResponse): AiredRepo =
        AiredRepo(aired.from, toRepo(aired.prop?: PropResponse(FromResponse(0, 0, 0), ToResponse(0, 0, 0))), aired.string, aired.to)

    override fun toRepo(demographic: DemographicResponse): DemographicRepo =
        DemographicRepo(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    override fun toRepo(from: FromResponse): FromRepo =
        FromRepo(from.day, from.month, from.year)

    override fun toRepo(genre: GenreResponse): GenreRepo =
        GenreRepo(genre.mal_id, genre.name, genre.type, genre.url)

    override fun toRepo(licensor: LicensorResponse): LicensorRepo =
        LicensorRepo(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    override fun toRepo(producer: ProducerResponse): ProducerRepo =
        ProducerRepo(producer.mal_id, producer.name, producer.type, producer.url)

    override fun toRepo(prop: PropResponse): PropRepo =
        PropRepo(toRepo(prop.from?: FromResponse(0, 0, 0)), toRepo(prop.to?: ToResponse(0, 0, 0)))

    override fun toRepo(related: RelatedResponse): RelatedRepo =
        RelatedRepo()

    override fun toRepo(studio: StudioResponse): StudioRepo =
        StudioRepo(studio.mal_id, studio.name, studio.type, studio.url)

    override fun toRepo(theme: ThemeResponse): ThemeRepo =
        ThemeRepo(theme.mal_id, theme.name, theme.type, theme.url)

    override fun toRepo(to: ToResponse): ToRepo =
        ToRepo(to.day, to.month, to.year)

}