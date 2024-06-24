package com.example.appmarvel.view.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.databinding.DialogDetailsBinding

class DetailsDialog(character: DataClass) : DialogFragment() {

    private var _binding: DialogDetailsBinding? = null
    private val binding get() = _binding!!
    private val characterComplete = character

    companion object {
        const val TAG = "DialogDetails"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val descriptionText = if (characterComplete.dataDescription.isEmpty()) {
            "Descrição não disponível"
        } else {
            characterComplete.dataDescription
        }

        with(binding) {
            tvNameCharacterDetails.text = characterComplete.dataTitle
            tvDescriptionDetails.text = descriptionText
            ivDetails.load(characterComplete.dataUrl + "." + characterComplete.extension)

            ivBackDetails.setOnClickListener { dialog?.dismiss() }
            btFavorite.setOnClickListener {
                Toast.makeText(requireContext(), "Clicou em favoritar.", Toast.LENGTH_LONG).show()

            }
            btShare.setOnClickListener {
                Toast.makeText(requireContext(), "Clicou em compartilhar.", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }
}