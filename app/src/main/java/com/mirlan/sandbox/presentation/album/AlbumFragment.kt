package com.mirlan.sandbox.presentation.album

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.domain.entity.Album
import com.mirlan.sandbox.utils.ConnectivityUtil
import com.mirlan.sandbox.utils.observe
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment : BaseFragment(R.layout.fragment_album) {
    private val viewModel: AlbumViewModel by viewModel()
    private val albumAdapter by lazy { AlbumAdapter(this::onSelectAlbum) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_rv.adapter = albumAdapter
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(requireContext())
            //viewModel.albums.observe(viewLifecycleOwner) {
            //albumAdapter.submitList(it)

    }

    private fun onSelectAlbum(album: Album) {
        val bundle = bundleOf("albumId" to album.id)
        navigate(R.id.action_album_screen_to_photo_screen, bundle)
    }
}