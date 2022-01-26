package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.season.ArchiveRepo
import com.lexwilliam.data.model.remote.season.SeasonArchiveRepo
import com.lexwilliam.data_remote.model.season.ArchiveResponse
import com.lexwilliam.data_remote.model.season.SeasonArchiveResponse
import javax.inject.Inject

interface ArchiveMapper {
    fun toRepo(archive: ArchiveResponse): ArchiveRepo
    fun toRepo(archives: SeasonArchiveResponse): SeasonArchiveRepo
}

class ArchiveMapperImpl @Inject constructor(): ArchiveMapper {
    override fun toRepo(archive: ArchiveResponse): ArchiveRepo =
        ArchiveRepo(archive.year, archive.season)

    override fun toRepo(archives: SeasonArchiveResponse): SeasonArchiveRepo =
        SeasonArchiveRepo(
            archives.request_hash,
            archives.request_cached,
            archives.request_cache_expiry,
            archives.archive.map { toRepo(it) }
        )

}