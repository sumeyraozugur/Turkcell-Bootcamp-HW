package com.sumeyra.noteapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeyra.noteapp.model.NoteModel
import com.sumeyra.noteapp.databinding.ItemNoteBinding

class NoteAdapter(
    private var onNoteClick: (NoteModel) -> Unit
) : RecyclerView.Adapter<NoteAdapter.GalleryHolder>() {

    private var noteList = listOf<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GalleryHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) =
        holder.bind(noteList[position])

    inner class GalleryHolder(private var itemGalleryBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemGalleryBinding.root) {

        fun bind(noteModel: NoteModel) = with(itemGalleryBinding) {
            tvTitle.text = noteModel.title
            root.setOnClickListener {
                onNoteClick(noteModel)
            }
        }
    }

    override fun getItemCount() = noteList.size

    fun updateList(updatedList: List<NoteModel>) {
        this.noteList = updatedList
        notifyDataSetChanged()
    }
}