package com.lexwilliam.risuto.mapper

//interface ArchiveMapper {
//    fun toDomain(archive: ArchiveRepo): Archive
//    fun toDomain(archives: SeasonArchiveRepo): SeasonArchive
//}
//
//class ArchiveMapperImpl @Inject constructor(): ArchiveMapper {
//    override fun toDomain(archive: ArchiveRepo): Archive =
//        Archive(archive.year, archive.season)
//
//    override fun toDomain(archives: SeasonArchiveRepo): SeasonArchive =
//        SeasonArchive(
//            archives.request_hash,
//            archives.request_cached,
//            archives.request_cache_expiry,
//            archives.archive.map { toDomain(it) }
//        )
//
//}