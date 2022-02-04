package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.common.*
import com.lexwilliam.risuto.model.common.*
import javax.inject.Inject

interface CommonMapper {
    fun toPresentation(aired: Aired): AiredPresentation
    fun toPresentation(demographic: Demographic): DemographicPresentation
    fun toPresentation(from: From): FromPresentation
    fun toPresentation(genre: Genre): GenrePresentation
    fun toPresentation(licensor: Licensor): LicensorPresentation
    fun toPresentation(producer: Producer): ProducerPresentation
    fun toPresentation(prop: Prop): PropPresentation
    fun toPresentation(related: Related): RelatedPresentation
    fun toPresentation(studio: Studio): StudioPresentation
    fun toPresentation(theme: Theme): ThemePresentation
    fun toPresentation(to: To): ToPresentation
}

class CommonMapperImpl @Inject constructor(): CommonMapper {
    override fun toPresentation(aired: Aired): AiredPresentation =
        AiredPresentation(aired.from, toPresentation(aired.prop!!), aired.string, aired.to)

    override fun toPresentation(demographic: Demographic): DemographicPresentation =
        DemographicPresentation(demographic.mal_id, demographic.name, demographic.type, demographic.url)

    override fun toPresentation(from: From): FromPresentation =
        FromPresentation(from.day, from.month, from.year)

    override fun toPresentation(genre: Genre): GenrePresentation =
        GenrePresentation(genre.mal_id, genre.name, genre.type, genre.url)

    override fun toPresentation(licensor: Licensor): LicensorPresentation =
        LicensorPresentation(licensor.mal_id, licensor.name, licensor.type, licensor.url)

    override fun toPresentation(producer: Producer): ProducerPresentation =
        ProducerPresentation(producer.mal_id, producer.name, producer.type, producer.url)

    override fun toPresentation(prop: Prop): PropPresentation =
        PropPresentation(toPresentation(prop.from!!), toPresentation(prop.to!!))

    override fun toPresentation(related: Related): RelatedPresentation =
        RelatedPresentation()

    override fun toPresentation(studio: Studio): StudioPresentation =
        StudioPresentation(studio.mal_id, studio.name, studio.type, studio.url)

    override fun toPresentation(theme: Theme): ThemePresentation =
        ThemePresentation(theme.mal_id, theme.name, theme.type, theme.url)

    override fun toPresentation(to: To): ToPresentation =
        ToPresentation(to.day, to.month, to.year)

}