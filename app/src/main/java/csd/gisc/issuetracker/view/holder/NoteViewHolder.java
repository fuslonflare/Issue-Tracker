package csd.gisc.issuetracker.view.holder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.model.Issue;
import csd.gisc.issuetracker.model.Note;

/**
 * Created by admin on 18/1/2561.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView textNoteAuthor;
    private AppCompatTextView textNoteDateTime;
    private AppCompatTextView textNoteDetail;

    public NoteViewHolder(View itemView) {
        super(itemView);

        textNoteAuthor = itemView.findViewById(R.id.text_note_create_by);
        textNoteDateTime = itemView.findViewById(R.id.text_note_create_time);
        textNoteDetail = itemView.findViewById(R.id.text_note_detail);
    }

    public void bindToNote(Note note) {
        textNoteAuthor.setText(note.getCreateBy());
        textNoteDateTime.setText(note.getDateTime(note.getCreateTime()));
        textNoteDetail.setText(note.getDetail());
    }
}
