package csd.gisc.issuetracker.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import csd.gisc.issuetracker.R;
import csd.gisc.issuetracker.enums.Status;
import csd.gisc.issuetracker.model.Issue;
import csd.gisc.issuetracker.model.Note;
import csd.gisc.issuetracker.view.holder.NoteViewHolder;

/**
 * Created by admin on 22/12/2560
 */

public class IssueDetailFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = IssueDetailFragment.class.getSimpleName() + "TAG";

    private RecyclerView listComment;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout fieldStatus;
    private ImageView imageStatus;
    private AppCompatTextView textIssueId;
    private AppCompatTextView textProductName;
    private AppCompatTextView textAssignTo;
    private AppCompatTextView textDetail;
    private AppCompatTextView textStatus;
    private TextInputEditText editMessage;
    private AppCompatButton buttonSend;

    private DatabaseReference mIssueRef;
    private DatabaseReference mCommentsRef;
    private DatabaseReference mRootRef;
    private FirebaseRecyclerOptions<Note> options;
    private FirebaseRecyclerAdapter<Note, NoteViewHolder> mAdapter;

    private ValueEventListener valueEventListener;

    private String issueKey;
    private Status status;

    public IssueDetailFragment() {
        super();
    }

    public static IssueDetailFragment newInstance(String issueKey) {
        IssueDetailFragment fragment = new IssueDetailFragment();
        Bundle args = new Bundle();
        args.putString("issue_key", issueKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            issueKey = getArguments().getString("issue_key");
        }

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mIssueRef = mRootRef.child("issues").child(issueKey);
        mCommentsRef = mRootRef.child("issues-notes").child(issueKey);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_detail, container, false);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        textIssueId = rootView.findViewById(R.id.text_issue_id);
        textProductName = rootView.findViewById(R.id.text_issue_product_name);
        textAssignTo = rootView.findViewById(R.id.text_issue_assign_to);
        textDetail = rootView.findViewById(R.id.text_issue_detail);
        textStatus = rootView.findViewById(R.id.text_issue_status);
        imageStatus = rootView.findViewById(R.id.image_status);
        editMessage = rootView.findViewById(R.id.edit_note_message);
        buttonSend = rootView.findViewById(R.id.button_send);
        fieldStatus = rootView.findViewById(R.id.linear_field_status);

        buttonSend.setOnClickListener(this);

        listComment = rootView.findViewById(R.id.list_comment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                listComment.getContext(), linearLayoutManager.getOrientation());
        listComment.addItemDecoration(dividerItemDecoration);
        listComment.setLayoutManager(linearLayoutManager);
        listComment.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onStart() {
        super.onStart();

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Issue issue = dataSnapshot.getValue(Issue.class);
                int resIconStatus = 0;
                if (issue != null) {
                    switch (status = issue.getStatus()) {
                        case New:
                            resIconStatus = R.drawable.ic_announcement;
                            break;
                        case InProgress:
                            resIconStatus = R.drawable.ic_track_changes;
                            break;
                        case Closed:
                            resIconStatus = R.drawable.ic_assignment_turned_in;
                            break;
                        default:
                            break;
                    }
                    if (getContext() != null) {
                        Glide.with(getContext())
                                .load(resIconStatus)
                                .into(imageStatus);
                    }
                    textIssueId.setText(String.valueOf(issue.getId()));
                    textProductName.setText(issue.getProductName());
                    textAssignTo.setText(issue.getAssignTo());
                    textDetail.setText(issue.getDetail());
                    textStatus.setText(issue.getStatus().name());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage(), databaseError.toException());
                showToast("Failed to load comments.");
            }
        };

        mIssueRef.addValueEventListener(valueEventListener);
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (valueEventListener != null) {
            mIssueRef.removeEventListener(valueEventListener);
        }

        mAdapter.stopListening();
    }

    /**
     * Save Instance State Here
     * */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /**
     * Restore Instance State Here
     * */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }

        Query query = mCommentsRef.orderByChild("createTime");

        options = new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Note, NoteViewHolder>(options) {
            @Override
            public NoteViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_comment, parent, false);

                return new NoteViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder,
                                            int position,
                                            @NonNull Note model) {
                holder.bindToNote(model);
            }
        };

        listComment.setAdapter(mAdapter);
    }

    private void showToast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_send) {
            String noteKey = mCommentsRef.push().getKey();
            String message = editMessage.getText().toString();
            String name = getUserName();
            long currentEpoch = System.currentTimeMillis() / 1000;

            Note note = new Note(name, currentEpoch, message);

            Map<String, Object> postNote = new HashMap<>();
            postNote.put(noteKey, note);

            mCommentsRef.updateChildren(postNote, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError,
                                       DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        editMessage.setText("");
                        editMessage.clearFocus();
                        hideKeyboard(getActivity());
                    } else {
                        Log.e(TAG, "Can't update children on " + databaseReference,
                                databaseError.toException());
                    }
                }
            });
        }
    }

    private String getUserName() {
        String name = "";
        if (getActivity() != null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences
                    ("preference_user_data", Context.MODE_PRIVATE);
            name = sharedPreferences.getString("name", "Somebody");
        }
        return name;
    }

    private void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
